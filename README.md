# any-parsing
include json study test

## Map<String, Object> parsing by Jackson
* Jackson 라이브러리를 사용해 FileRead.read("json_text.txt") 로 파일을 읽습니다.
* AnyParser.parseByMap("json") 으로 파싱합니다.

## 전체적인 구조

1. public MapType constructMapType(Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) 이 호출되어서
JavaType 객체로 변환되어 2. 로 넘어감

2. public MapType constructMapType(Class<? extends Map> mapClass, JavaType keyType, JavaType valueType)
검증과 바인딩 단계

3. TypeBindings bindings = TypeBindings.createIfNeeded(mapClass, new JavaType[]{keyType, valueType});
TypeBindings.createIfNeeded 을 통해 바인딩된 MapType 리턴

## Jackson JAVADOC
* [JACKSON 2.9 JAVADOC](http://fasterxml.github.io/jackson-databind/javadoc/2.9/)
