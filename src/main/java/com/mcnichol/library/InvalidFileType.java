package com.mcnichol.library;

public class InvalidFileType extends RuntimeException{
    InvalidFileType(String msg){
        super(msg);
    }
}
