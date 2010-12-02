@echo off
echo --------------------------------------
echo `
echo There will be lots of messages here
echo which are not removed in hurry.
echo `
echo Now you have started the server...
echo you can minimize this command shell and
echo open a browser, and for this demo app
echo `
echo Browse "http://localhost/tweet.htm"
echo `
echo There actually it's very clear...
echo In TweeTexT Input Box
echo Try to put any <SCRIPT/> to test it
echo and press 'Submit'; it'll show added on success
echo Then click 'Read My...' button to inject that <SCRIPT/> from server into Page
echo `
echo You can even check all TweeTexT in CSV file at Web-Root
echo --------------------------------------


cd > tmpfileCurrDir
set /p currDir= < tmpFileCurrDir
del tmpFileCurrDir

java -jar abkSiteHoster.jar -r "%currDir%" -os WIN32 -xss