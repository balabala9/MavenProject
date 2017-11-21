package org.kotlin

infix fun List<String>.join(split: String): String {
    return this.reduce{x,y ->
        if(x.isNullOrBlank() || y.isNullOrBlank()){
            x+y
        }else{
            x+split+y
        }
    }
}

fun bindValue(vararg arg:String):String{
    return arg.reduce{x,y ->
        if(x.isNullOrBlank() || y.isNullOrBlank()){
            x+y
        }else{
            x+"!=end=!"+y
        }
    }
}

fun <T> runfun(block:()->T):T{
    return block()
}