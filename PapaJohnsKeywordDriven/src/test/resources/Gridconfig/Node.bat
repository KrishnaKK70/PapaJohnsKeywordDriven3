@echo off

start /d "D:\maven dump" java -Dwebdriver.chrome.driver="D:\maven dump\chromedriver.exe" -jar selenium-server-standalone-3.8.1.jar -role webdriver -nodeConfig "D:\workspace\PapaJohnsKeywordDriven\src\test\resources\Gridconfig\chrome.json" 
start /d "D:\maven dump" java -Dwebdriver.gecko.driver="D:\maven dump\geckodriver.exe" -jar selenium-server-standalone-3.8.1.jar -role webdriver -nodeConfig "D:\workspace\PapaJohnsKeywordDriven\src\test\resources\Gridconfig\firefox.json"
start /d "D:\maven dump" java -Dwebdriver.ie.driver="D:\maven dump\IEDriverServer.exe" -jar selenium-server-standalone-3.8.1.jar -role webdriver -nodeConfig "D:\workspace\PapaJohnsKeywordDriven\src\test\resources\Gridconfig\ie.json"