# How to

Class Available

- BadRequestException
- NotFoundException
- UnAuthorizationException
- UnProcessableException
- BadRequestException

### Using

build project or copy java-exception-handle-lib-0.0.1-SNAPSHOT.jar to your project

 
Copy to dir libs and config gradle example as below 
```
repositories {
	mavenCentral()
	flatDir {
		dirs 'libs'
	}
}

dependencies {
    .....
    compile group: 'com.osdnu', name: 'java-exception-handle-lib', version: '0.0.1-SNAPSHOT'
}

```
Reload gradle

```
public Book saveBook(Book book) throws UnProcessableException {
    List<Book> books = findBooksByBookName(book.getName());
    if (isAlready(books)) throw new UnProcessableException("20X-01", "Book name is already.");
    return save(book);
}
```

Response (Status Code == 422)

```Json
{
    "errorMessage": "Book name is already.",
    "errorCode": "20X-01"
}
```