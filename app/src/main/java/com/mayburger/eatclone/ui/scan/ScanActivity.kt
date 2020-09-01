package com.mayburger.eatclone.ui.scan

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.CountDownTimer
import android.view.TextureView
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.camera.core.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.ActivityScanBinding
import com.mayburger.eatclone.ui.base.BaseActivity
import com.mayburger.eatclone.util.QrCodeAnalyzer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ScanActivity : BaseActivity<ActivityScanBinding, ScanViewModel>(),
    ScanNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_scan
    override val viewModel: ScanViewModel by viewModels()

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, ScanActivity::class.java))
        }

        private const val REQUEST_CAMERA_PERMISSION = 10
    }

    private lateinit var textureView: TextureView
    val timer = object : CountDownTimer(1000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
        }
        override fun onFinish() {
            viewModel.isShowing.set(false)
            viewModel.isLoaded.set(false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
        textureView = findViewById(R.id.texture_view)
        if (isCameraPermissionGranted()) {
            textureView.post { startCamera() }
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA_PERMISSION
            )
        }
        CoroutineScope(Main).launch {
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
                if (currentCode != it.rawValue){
                    viewModel.isShowing.set(false)
                    viewModel.isLoaded.set(false)
                }
                if (it.rawValue?.contains("eatapp:restaurant-") == true) {
                    if (viewModel.isShowing.get() == false) {
                        viewModel.isShowing.set(true)
                        viewModel.getRestaurant(it.rawValue ?: "")
                        currentCode = it.rawValue?:""
                    }
                }
            }
        }
        imageAnalysis.analyzer = qrCodeAnalyzer
        CameraX.bindToLifecycle(this, preview, imageAnalysis)
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

    private fun isCameraPermissionGranted(): Boolean {
        val selfPermission =
            ContextCompat.checkSelfPermission(baseContext, Manifest.permission.CAMERA)
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
                Toast.makeText(this, "Camera permission is required.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
