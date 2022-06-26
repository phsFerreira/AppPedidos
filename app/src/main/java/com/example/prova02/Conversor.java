package com.example.prova02;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;

public class Conversor {

    @TypeConverter
    public byte[] BitmapParaByte (Bitmap imagem){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        imagem.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();
    }

    @TypeConverter
    public Bitmap ByteParaBitmap (byte[] imagemByte){
        Bitmap imagem = BitmapFactory.decodeByteArray(imagemByte, 0, imagemByte.length);
        return imagem;
    }
}
