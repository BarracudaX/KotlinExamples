package structural

/**
 * Faced design pattern provides an interface to a library, framework or related classes in order to provide interfaces with simplified API and/or most common use cases.
 */

class VideoFile(val name: String,val data: ByteArray)

interface VideoCompressor{

    fun compress(videoFile: VideoFile) : VideoFile

}

interface VideoEncoder{

    fun encode(videoFile: VideoFile) : VideoFile

}

interface VideoUploader{

    fun upload(videoFile: VideoFile)

}

class VideoEditor(private val videoCompressor: VideoCompressor,private val videoEncoder: VideoEncoder,private val videoUploader: VideoUploader){

    fun publish(videoFile: VideoFile){
        val finalVideoFile = videoCompressor.compress(videoEncoder.encode(videoFile))

        videoUploader.upload(finalVideoFile)
    }

}