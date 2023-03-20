package creational

import java.io.File

data class Mail( val to: String,val cc: List<String> = emptyList(),val bcc: List<String> = emptyList(),val title: String? = null, val message: String = "",val attachments: List<File> = emptyList())