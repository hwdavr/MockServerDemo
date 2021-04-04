# Mock Server Demo

A demo app to show how to use MockWebServer to mock the response for manual test, auto UI test and junit test. 

## General new keystore for SSL pinning test
Remove the local keystore file: localhost.keystore.jks
Generate keystore:
```
cd app/src/debug/resources
keytool -genkey -v -keystore localhost.keystore.jks -alias localhost -ext SAN=dns:localhost -keypass 123456 -storepass 123456 -keyalg RSA -keysize 2048 -validity 10000 -storetype BKS -provider org.bouncycastle.jce.provider.BouncyCastleProvider -providerpath ../../../../bcprov-jdk15on.jar
```
Extract certificate:
```
keytool -export -alias localhost -keystore ./localhost.keystore.jks -file ../res/raw/certificate.cer -storetype BKS -provider org.bouncycastle.jce.provider.BouncyCastleProvider -providerpath /path/to/bouncycastle.jar
```
Calculate certificate pin:
```
openssl x509 -inform der -in ../res/raw/certificate.cer -pubkey --noout |
openssl rsa -pubin -outform der |
openssl dgst -sha256 -binary |
openssl enc -base64
```