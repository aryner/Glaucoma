<%-- 
    Document   : uploadData
    Created on : Oct 28, 2014, 9:02:24 AM
    Author     : aryner
--%>

<h3>Upload CSV files of data</h3>
<p style="width:25%;text-align:center;">
Make sure to also upload the PDFs that go with the CSV files you are uploading 
in the upload pictures page if you haven't already.
</p>

<br><br>
<form action="dataUpload" method="POST" enctype="multipart/form-data">
	<input type="file" multiple="multiple" name="csvs" style="margin-right:20px;">
	<input type="submit" value="Submit" class="btn bigBtn">
</form>