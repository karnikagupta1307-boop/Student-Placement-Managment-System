package com.example.placement;
import android.net.Uri;
/** Local document metadata only; localUri is a content URI, never a filesystem path. */
public class StudentDocument { public String type,fileName,status; public Uri localUri; public StudentDocument(String type,String fileName,Uri uri){this.type=type;this.fileName=fileName;localUri=uri;status="Selected Locally";} }
