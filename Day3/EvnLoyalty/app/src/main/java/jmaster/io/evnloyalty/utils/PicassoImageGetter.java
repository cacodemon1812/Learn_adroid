//package jmaster.io.evnloyalty.utils;
//
//import android.content.res.Resources;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.media.ThumbnailUtils;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.text.Html;
//import android.text.TextUtils;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.RequestManager;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//
//import java.io.IOException;
//import java.lang.ref.WeakReference;
//
//public class PicassoImageGetter implements Html.ImageGetter {
//    private final TextView mTextView;
//
//    /**
//     * Construct an instance of {@link android.text.Html.ImageGetter}
//     * @param view      {@link android.widget.TextView} that holds HTML which contains $lt;img> tag to load
//     */
//    public PicassoImageGetter(TextView view) {
//        mTextView = view;
//    }
//
//    @Override
//    public Drawable getDrawable(String source) {
//        if (TextUtils.isEmpty(source)) {
//            return null;
//        }
//        final Uri uri = Uri.parse(source);
//        if (uri.isRelative()) {
//            return null;
//        }
//        String filename = source;
//        final URLDrawable urlDrawable = new URLDrawable(mTextView.getResources(), null);
//        Bitmap bitmap = null;
//        if((bitmap = Utils.loadImageFromStorage(mTextView.getContext(), filename)) == null) {
//            new LoadFromUriAsyncTask(mTextView, urlDrawable, filename).execute(uri);
//        }else{
//            urlDrawable.mDrawable = new BitmapDrawable(mTextView.getResources(), bitmap);
//            int width =  urlDrawable.mDrawable.getIntrinsicWidth();
//            int height =  urlDrawable.mDrawable.getIntrinsicHeight();
//            urlDrawable.setBounds(0, 0, width, height);
//            urlDrawable.mDrawable.setBounds(0, 0, width, height);
//            // force redrawing bitmap by setting text
//            mTextView.setText(mTextView.getText());
//            bitmap = null;
//        }
//        return urlDrawable;
//    }
//}
//
//class LoadFromUriAsyncTask extends AsyncTask {
//    private final WeakReference mTextViewRef;
//    private final URLDrawable mUrlDrawable;
//    private final RequestManager mImageUtils;
//    private final String mFilename ;
//
//    public LoadFromUriAsyncTask(TextView textView, URLDrawable urlDrawable, String filename) {
//        mImageUtils = Glide.with(textView.getContext());
//        mTextViewRef = new WeakReference(textView);
//        mUrlDrawable = urlDrawable;
//        mFilename = filename;
//    }
//
//    @Override
//    protected Bitmap doInBackground(Uri... params) {
//        try {
//            return mImageUtils.asBitmap().load(params[0])
//                    .centerCrop()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL).submit()
//                    .get();
//        } catch (IOException e) {
//            return null;
//        }
//    }
//
//    @Override
//    protected void onPostExecute(Bitmap result) {
//        if (result == null) {
//            return;
//        }
//        if (mTextViewRef.get() == null) {
//            return;
//        }
//        TextView textView = (TextView) mTextViewRef.get();
//        // change the reference of the current mDrawable to the result
//        // from the HTTP call
//        if(result.getWidth() > textView.getWidth()){
//            int thumbWidth = (int)(2.0 *textView.getWidth() / 3);
//            int thumbHieght = (int)(1.0 * thumbWidth * result.getHeight()/result.getWidth());
//            result = ThumbnailUtils.extractThumbnail(result, thumbWidth, thumbHieght);
//        }
//        mUrlDrawable.mDrawable = new BitmapDrawable(textView.getResources(), result);
//        // set bound to scale image to fit width and keep aspect ratio
//        // according to the result from HTTP call
//            /*int width = textView.getWidth();
//            int height = Math.round(1.0f * width *
//                    mUrlDrawable.mDrawable.getIntrinsicHeight() /
//                    mUrlDrawable.mDrawable.getIntrinsicWidth());*/
//        int width =  mUrlDrawable.mDrawable.getIntrinsicWidth();
//        int height =  mUrlDrawable.mDrawable.getIntrinsicHeight();
//        mUrlDrawable.setBounds(0, 0, width, height);
//        mUrlDrawable.mDrawable.setBounds(0, 0, width, height);
//        // force redrawing bitmap by setting text
//        textView.setText(textView.getText());
//        Utils.saveToInternalStorage(textView.getContext(), result, mFilename);
//    }
//
//}
//
// class URLDrawable extends BitmapDrawable {
//     public Drawable mDrawable;
//
//    public URLDrawable(Resources res, Bitmap bitmap) {
//        super(res, bitmap);
//    }
//
//    @Override
//    public void draw(Canvas canvas) {
//        if(mDrawable != null) {
//            mDrawable.draw(canvas);
//        }
//    }
//}
