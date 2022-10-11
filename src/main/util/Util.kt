package main.util

fun String?.isInteger(): Boolean {
    val str: String? = this
    if(str == null){
        return false
    }else{
        val length = str.length
        if (length == 0) {
            return false
        }
        var i = 0
        if (str[0] == '-') {
            if (length == 1) {
                return false
            }
            i = 1
        }
        while (i < length) {
            val c = str[i]
            if (c < '0' || c > '9') {
                return false
            }
            i++
        }
        return true
    }
}
fun String.extractDigits(): String {
    val str = this.trim { it <= ' ' } // Using `this`, i.e. the receiving instance
    var digits = ""
    var chrs: Char
    for (element in str) {
        chrs = element
        if (Character.isDigit(chrs)) {
            digits += chrs
        }
    }
    return digits
}