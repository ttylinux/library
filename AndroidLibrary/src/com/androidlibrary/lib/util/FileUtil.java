package com.androidlibrary.lib.util;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.os.Environment;

public class FileUtil {

  private Context _context;

  public FileUtil(Context context) {
    _context = context;
  }

  /**
   * return an app's internal dir
   */
  public File getAnInternalDir() {
    return _context.getFilesDir();
  }

  /**
   * return an app's internal cache dir
   */
  public File getACacheInternalDir() {
    return _context.getCacheDir();
  }

  /**
   * create a file in the internal dir
   */
  public File createAFileInInternalDir(String fileName) {
    File file = new File(getAnInternalDir(), fileName);

    return file;
  }

  /**
   * create a cacheFile in an Internal Cache dir
   */
  public File createACacheFileInInternalDir(String fileName) throws IOException {
    File file;
    file = File.createTempFile(fileName, null, getACacheInternalDir());
    return file;
  }

  /**
   * check if the external storage is mounted(if mounted, it is available)
   */
  private boolean isExternalStorageAvailable() {
    String state = Environment.getExternalStorageState();
    if (Environment.MEDIA_MOUNTED.equals(state)) {
      return true;
    }
    return false;
  }

  /**
   * 
   * 
   * Required API level 8
   * 
   * return a dir saved in external storage. when the app is uninstalled, files saved in this dir
   * will not be deleted.
   * 
   * @param dirType (use Constant like Environment.DIRECTORY_PICTURES,These directory names ensure
   *        that the files are treated properly by the system )
   * @throws Exception
   */
  public File getAnPublicExternalStorageDir(String albumName, String dirType) throws Exception {

    if (!isExternalStorageAvailable()) {
      throw new Exception("External Storage is unavailable");
    }

    File file = new File(Environment.getExternalStoragePublicDirectory(dirType), albumName);

    if (!(file.exists() && file.isDirectory())) {
      if (!file.mkdirs()) {
        throw new Exception("Directory not created");
      }
    }

    return file;

  }

  /**
   * required API level 8
   * 
   * return a dir saved in external storage.when the app is uninstalled, files saved in this dir
   * will be deleted.
   * 
   * @param dirType (use Constant like Environment.DIRECTORY_PICTURES,These directory names ensure
   *        that the files are treated properly by the system ),can be null,if null,will return the
   *        root directory for your app's private directory
   * 
   */
  public File getAnPrivateExternalStorageDir(String albumName, String dirType) throws Exception {

    if (!isExternalStorageAvailable()) {
      throw new Exception("External Storage is unavailable");
    }

    File file = new File(_context.getExternalFilesDir(dirType), albumName);
    if (!(file.exists() && file.isDirectory())) {
      if (!file.mkdirs()) {
        throw new Exception("Directory not created");
      }
    }
    return file;
  }

  /**
   * 
   * return a dir for saving cache file in externalStorage. Files saved in this dir will be deleted
   * when the app uninstalled.
   * 
   */
  public File getAnPrivateCacheExternalStorageDir() {
    return _context.getExternalCacheDir();

  }

  /**
   * Return the primary external storage directory. files saved in this dir will be deleted, when
   * app uninstalled.
   */
  public File getAnExternalStorageDir() {
    return Environment.getExternalStorageDirectory();
  }

}
