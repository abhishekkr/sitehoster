ABK (Secure) SiteHoster
https://sourceforge.net/downloads/sitehoster/
-by, ABK [AbhishekKr]
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
Major Changes:
_______________
[ v1.0beta RC2 ]|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
[] there was a requirement raised by few users on un-availability of <SCRIPT/> in <BODY/> tag after the applying XSS-Patch; which will slow Page Rendering.
So, this release is just with a small feature of allowing a <SCRIPT/> to be added to <BODY/> tag.

For this, web developers need to add <SCRIPT/> to head with 'DEFER' keyword like the one supported in Internet Explorer.
It's just that it doesn't expect browser to take care of it.
But, pulls out all <SCRIPT/> with 'DEFER' from <HEAD/> and pushes it in Active Zone of <BODY/> tag.

Eg.
a Page like
[-----]
<html>
<head>
<TITLE>ABK</TITLE>
<SCRIPT type='text/javascript' DEFER>alert('body1');</script>
<script DEFER='DEFER'>alert('body2');</SCRIPT>
<Script>alert('head');</scripT>
<SCRIPT src='dontknow.js' body/>
<SCRIPT type='text/javascript'> alert('DEFer');</script>
</head>
<body>
a test page
</body>
</html>
[-----]

is changed to a Page
[-----]
<html>
<head>
<TITLE>ABK</TITLe>
<Script>alert('head');</scripT>
<SCRIPT src='dontknow.js' body/>
<SCRIPT type='text/javascript'> alert('DEFer');</script>
</head>
<BD><BODY>
<script type='text/javascript'> x=document.getElementsByTagName("BODY");x[0].innerHTML = "a test page"</script>
<script DEFER> function b(){alert('its deferred 1');}</script>
<script DEFER="DEFER"> function c(){alert('its deferred 2');}</script></BODY></BD>
</html>
[-----]

_______________
[ v1.0beta RC1 ]|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
[] command-line method for specifying platform has been provided using '-o' switch
	that can refer to Linux as '-o POSIX'; Windows is default mode
	eg: 

[] XSS Patch has been implemented; user can test browsing any file, the <SCRIPT/> in <BODY/> tag 
	should become inactive as per tests.
	This will work on all Web-Pages (plain HTML pages and 'a-cgi' supported both).

[] yet I have not implemented any CGI-support
	for time-being to present a similar feature for users to test XSS-Patch using
	Server-Side Scripting, I've include an 'a-cgi' support that does nothing more
	but simple takes the GET request and find any script at path '/a-cgi/*', then
	eg: [ GET /a-cgi/addTweet.py?name=ABK&text=my%20new%20tweet HTTP/1.1 ]
		so any script to be capable for 'a-cgi' must be existing at  '/a-cgi/*' location.
		then it like CGI it identifies the application metioned in its first line
		as '#!python' or other and executes & sends the output of following process
		[ python addTweet.py name=ABK text=my%20new%20tweet ]
		as HTTP Response to Client (after applying XSS-Patch)
	I've provided an example along-with this version using all of it successfully, that
	can be used as an example to implement it.
	Or even reuse the scripts in it to get proper access to GET Request Variables provided.
	as in example's script 'aCGIGetVar.py' could be imported for the task.
	And 'addTweet.py' could be referred on how to use 'aCGIGetVar.py' module.

	This even allows you to handle GET Variables in similar style arguments fashion
	by any pre-compiled binary.
	it's just that the location of that binary will be '/a-cgi/bin/*'

NOTE:: till now only GET Requests are being handled by the server.

[ v1.0beta ]|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
[] Partial Implementation of HTTP v1.1; earlier implementation were HTTPv0.9
	or a real bad HTTP v1.0.
	From this release implementation of HTTP v1.1 has been started and will
	be extended forward.

[] Syntax has been changed (for better)
	>> currently the cmd-line options are same but with switches
		so like their order doesn't matter and except Doc_Root, 
		both of the other or anyone can be skipped.
		Earlier, to change default port one was forced to specify 
		default page.
	>> mandatory Doc_Root directory/folder path is to ber specified with
		switch -r . eg.: [java -jar httpV1beta.jar -r C:\temp]

[] A check has been implemented for Doc_Root path existence, not allowing to
	provide a non-existing path.

[] Earlier releases had a bug in ServerCode Function implementation for 
	Response Genearation; that has been fixed.

[] Earlier release had several other bugs regarding 'Content' baed HTTP Headers;
	those have been fixed in tis release.

|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
PreRequirement:
_______________
Install Java Virtual Machine on your Computer System.


|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
Usage Syntax :
_______________
[] Starting HTTP Server hosting web files in <C:\WebDoc\> folder with <index.htm> as default file to open when root of IP accessed (it opens at Port 80 by default) on Windows
"java -jar http09_v0.5a.jar -r C:\WebDoc\ -f index.htm" -o WIN32

[] Starting HTTP Server hosting web files in <C:\WebDoc\> folder with <index.htm> as default file to open when root of IP accessed; and 1234 is Port to be opened at (so access like http://IP:1234/index.htm) on Linux
"java -jar http09_v0.5a.jar -r C:\WebDoc\ -f index.htm -p 1234" -o POSIX

[] Starting HTTP Server hosting web files in <C:\WebDoc\> folder; and 1234 is Port to be opened at (so access like http://IP:1234/) on Windows
"java -jar http09_v0.5a.jar -r C:\WebDoc\ -p 1234" -o WIN32


NOTE:
default mode is Windows
to make it work in Linux, the switch associated is -o POSIX
to make it work in Windows, the switch associated is -o WIN32