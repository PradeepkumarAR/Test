## Pattern -  MVVM

####Package Structure
- **database** - handles operation deals with db opeartions.
- **di** - handles operations related to dependency injection.
- **model** - defines the structure of db table and data structure of news/articles.
- **network** - handles operations related to i/o.
- **repository** - takes care of data source decision at any given point of time.
- **ui** - deals with UI. Followed standard** Master-Detail** flow on smallest-width-900dp.
- **ui/viewmodel** - deligates calls b/w repository and the Activity/Fragment(View). Used  ViewModel to avoid manual disposing.