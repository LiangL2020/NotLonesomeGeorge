package com.example.notlonesomegeorge

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.notlonesomegeorge.Configs.createDefaultProfileDir
import com.example.notlonesomegeorge.databinding.ActivityCameraBinding
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


private const val TAG = "activityTag";

class CameraActivity: AppCompatActivity() {

    val REQUEST_CODE = 200

    private lateinit var binding: ActivityCameraBinding
    private lateinit var photoName: String
    private lateinit var logName: String
    private lateinit var cam_btn: ImageButton
    private lateinit var cam_view: ImageView
    private lateinit var currentPhotoPath: String
    private lateinit var photoURI: Uri
    private var count = 0

    /*
    // From Demo Code
    private val takePhoto = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ){
        didTakePhoto: Boolean ->
        Log.i(TAG,"photo taken: $didTakePhoto")
        if(didTakePhoto){
            Log.i(TAG,"photo successfully taken and saved to $photoName")
            updatPhoto(photoName, binding.cameraMainView)
        }
    }
    // From Demo Code Ends
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        Log.i(TAG, "in onCreate() CameraActivity...")

        cam_view = findViewById(R.id.camera_main_view)
        cam_btn = findViewById(R.id.camera_main_capture_btn)


        // From Online; Android Developer
        cam_btn.setOnClickListener {
            Log.i(TAG, "camera capture btn pressed...")
            // Android Developer
            dispatchTakePictureIntent()

            // Online
//            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            try {
//                Log.i(TAG, "trying startActivityForResult()...")
//                startActivityForResult(cameraIntent, REQUEST_CODE)
//            } catch (e: ActivityNotFoundException) {
//                Log.i(TAG, "some camera errors have occurred...")
//                Toast.makeText(this, "Error: camera problems...", Toast.LENGTH_SHORT).show()
//            }
        }
        // From Online; Developer Ends

        /*
        // From Demo Code
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configPicasso()

        binding.cameraMainCaptureBtn.setOnClickListener{
            Log.i(TAG, "camera capture button 1 pressed...")
            val image_count = "${count}"
            count = count + 1
            photoName = "img_${image_count}.jpg"
            logName = "${Date()}.txt"

            val photoURI = createUri(this, photoName)
            takePhoto.launch(photoURI)
        }

        val camIntent = takePhoto.contract.createIntent(this, Uri.parse("test"))
        binding.cameraMainView.isEnabled = canResolveIntent(camIntent)

        binding.cameraReveal.setOnClickListener {
            readFromFile()
        }
        // Demo Code Ends
       */
    }

    /*
    // From Demo Code
    private fun readFromFile(){
        val fileContents = "Here are the stored files:"
        val file = File(getDefaultLogDir(this),logName)
        file.writeBytes(fileContents.toByteArray())
        binding.cameraSomeText.text = String(file.readBytes())
    }

    private fun updatPhoto(photoFileName: String?, view: ImageView){
        if(view.tag != photoFileName){
            val photoFile = photoFileName?.let{
                File(getDefaultProfileDir(this), it)
            }
            if(photoFile?.exists() == true){
                view.doOnLayout { measuredView ->
                    val scaledBitmap =  getScaledBitmap(
                        photoFile.path,
                        measuredView.width,
                        measuredView.height
                    )
                    view.setImageBitmap(scaledBitmap)
                    view.tag = photoFileName
                }
            }else{
                view.setImageBitmap(null)
                view.tag = null
            }
        }
    }
    // Demo Code Ends
    */

    // From Android Developer
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        Log.i(TAG,"in onActivityResult() CameraActivity...")
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
//            val imageBitmap = data?.extras?.get("data") as? Bitmap
//            cam_view.setImageBitmap(imageBitmap)
//        }
//    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.i(TAG,"in onActivityResult() CameraActivity...")
        super.onActivityResult(requestCode, resultCode, data)
//        if (data == null){
//            return;
//        } else
        if (requestCode === REQUEST_CODE && resultCode === RESULT_OK) {
            val bitmap: Bitmap
            try {
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, photoURI)
                cam_view.setImageBitmap(bitmap)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
//        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
////            val imageBitmap = data.extras.get("data") as Bitmap
////            cam_view.setImageBitmap(imageBitmap)
//        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        Log.i(TAG,"in createImage() CameraActivity...")
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = createDefaultProfileDir(this)

        // 1. check storageDir exists?
        // 2. mkdir storageDir

        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    private fun dispatchTakePictureIntent() {
        Log.i(TAG,"in dispatchTakePictureIntent() CameraActivity...")
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File
                    Log.i(TAG,"ERROR IN RESOLVING ACTIVITY...")
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
//                    val photoURI: Uri = FileProvider.getUriForFile(
                    photoURI = FileProvider.getUriForFile(
                        this,
                        "com.example.android.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_CODE)
                }
            }
        }
    }

    private fun galleryAddPic() {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            val f = File(currentPhotoPath)
            mediaScanIntent.data = Uri.fromFile(f)
            sendBroadcast(mediaScanIntent)
        }
    }
    // From Android Developer Ends
}



