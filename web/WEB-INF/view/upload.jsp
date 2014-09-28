<%-- 
    Document   : upload
    Created on : Sep 11, 2014, 4:50:55 PM
    Author     : aryner
--%>

<h3>Upload pictures to be graded</h3>

<form action='uploadPictures' method="POST" enctype="multipart/form-data">
	Choose which section to add to
	<select name="type" class="btn bigBtn"> 
		<option value="HVF">HVF</option>
		<option value="FDT">FDT</option>
		<option value="OCT">OCT</option>
		<option value="stereo">Stereo</option>
		<option value="3Nethra">3Nethra</option> 
	</select>
	<p>Select which pictures to upload</p>
	<input type="file" multiple="multiple" name="pictures">
	<input type="submit" value="Submit" class="btn bigBtn">
</form>