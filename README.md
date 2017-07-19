# GithubApp

<b>master</b> branch contains version compatible with Android Studio 2.4.

<b>as3.0</b> branch contains version compatible with Android Studio 3.0

Brief overview of components used:-

1. MVP architecure is followed across.
2. Dependency Injection using Dagger 2 is done.
3. Network layer is developed using Retrofit 2 in conjunction with RxJava 2. This should however be done in more scalable way in separate data layer, maybe using Repository pattern and not simply in the presenter.
4. Used android's memory LruCache to minimize the network usage for most recently fetched comments(Again, data layer should be used). However, kind of overkill having memory cache for plain texts(could be done even with simple instance and then a disk cache), but just to elaborate. 
5. Comparator used for out of the box collections sorting and utilized RxJava's operators to get it working.
6. Couple of test cases written using JUnit 4 done for a utility method which does the date/time conversion. 
7. Handled some of the might be issues like network error, provided refresh functionality to fetch again.
