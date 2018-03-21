# convert-sample
sample

## dtd -> xsd <br />

[trang.jar](https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/jing-trang/trang-20091111.zip)

`java -jar trang.far validation_1_1.dtd validation_1_1.xsd`

## xsd -> java <br />
`xjc -p org.sample.struts.validation -encoding UTF-8 src/test/xsd/validation_1_1.xsd`
### memo
- xjcコマンドでエラーが発生したのでvalidation_1_1.xsdを修正<br />
- <arg0\>〜<arg3\>は複数設定できるようなでさらにvalidation_1_1.xsdを修正<br />
