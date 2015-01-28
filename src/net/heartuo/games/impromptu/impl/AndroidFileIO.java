package net.heartuo.games.impromptu.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.File;

import android.content.res.AssetManager;
import android.os.Environment;

import net.heartuo.games.impromptu.*;

public class AndroidFileIO implements FileIO {
	AssetManager assets;
	String externalStoragePath;
	
	public AndroidFileIO(AssetManager assets) {
		this.assets = assets;
		this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
	}
	
	@Override
	public InputStream readAsset(String fileName) throws IOException {
		return assets.open(fileName);
	}
	
	@Override
	public InputStream readFile(String fileName) throws IOException {
		return new FileInputStream(externalStoragePath + fileName);
	}
	
	@Override
	public OutputStream writeFile(String fileName) throws IOException {
		return new FileOutputStream(externalStoragePath + fileName);
	}
}
