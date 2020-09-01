package com.mayburger.eatclone.ui.scan.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.CountDownTimer
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.FragmentScanBinding
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.ui.base.BaseFragment
import com.mayburger.eatclone.ui.menu.main.MenuFragment
import com.mayburger.eatclone.util.QrCodeAnalyzer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_scan.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ScanFragment : BaseFragment<FragmentScanBinding, ScanFragmentViewModel>(),
    ScanFragmentNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_scan
    override val viewModel: ScanFragmentViewModel by viewModels()

    companion object {
        private const val REQUEST_CAMERA_PERMISSION = 10
    }

    private lateinit var textureView: TextureView
    val timer = object : CountDownTimer(1000, 500) {
        override fun onTick(millisUntilFinished: Long) {
        }

        override fun onFinish() {
            viewModel.reset()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigator = this
        navController = Navigation.findNavController(view)
        textureView = texture_view
        viewDataBinding?.lifecycleOwner = viewLifecycleOwner
        if (isCameraPermissionGranted()) {
            textureView.post { startCamera() }
        } else {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(Manifest.permission.CAMERA),
                    REQUEST_CAMERA_PERMISSION
                )
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            viewModel.isQrShowing.set(true)
        }
    }


    private fun startCamera() {
        val previewConfig = PreviewConfig.Builder()
            .setLensFacing(CameraX.LensFacing.BACK)
            .build()
        val preview = Preview(previewConfig)
        preview.setOnPreviewOutputUpdateListener { previewOutput ->
            val parent = textureView.parent as ViewGroup
            parent.removeView(textureView)
            textureView.setSurfaceTexture(previewOutput.surfaceTexture)
            parent.addView(textureView, 0)
        }
        val imageAnalysisConfig = ImageAnalysisConfig.Builder()
            .build()
        val imageAnalysis = ImageAnalysis(imageAnalysisConfig)

        var currentCode = ""
        val qrCodeAnalyzer = QrCodeAnalyzer { qrCodes ->
            qrCodes.forEach {
                timer.start()
                if (currentCode != it.rawValue) {
                    viewModel.reset()
                }
                if (it.rawValue?.contains("eatapp:restaurant-") == true) {
                    if (viewModel.isShowing.get() == false) {
                        viewModel.isShowing.set(true)
                        viewModel.getRestaurant(it.rawValue ?: "")
                        currentCode = it.rawValue ?: ""
                    }
                } else {
                    viewModel.invalid()
                }
            }
        }
        imageAnalysis.analyzer = qrCodeAnalyzer
        CameraX.bindToLifecycle(viewLifecycleOwner, preview, imageAnalysis)
    }

    override fun onClickRestaurant(it: RestaurantDataModel) {
        navController.navigate(
            R.id.action_main_to_menu,
            MenuFragment.getBundle(it),
            null,
            null
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

    private fun isCameraPermissionGranted(): Boolean {
        val selfPermission =
            activity?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.CAMERA) }
        return selfPermission == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (isCameraPermissionGranted()) {
                textureView.post { startCamera() }
            } else {
                onError("We need camera permission to scan QR Codes!")
                finishActivity()
            }
        }
    }
}
