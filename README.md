# Cheap and Healthy Search App - Backend Documentation

## Overview

This project implements a search application for finding cheap and healthy recipes from Reddit posts. The backend is responsible for processing and indexing post data, as well as providing search functionality.

## Backend Developer Responsibilities

As the Backend Developer, my role was to:
1. Implement the core search functionality
2. Integrate with the Data Wrangler's post reader and Algorithm Engineer's hashtable
3. Provide statistics about the dataset
4. Ensure proper error handling and data processing

## Key Components

### Main Classes

- `CHSearchBackendBD`: The main backend class that implements all search functionality
- `HashtableWithDuplicateKeysBD`: A placeholder implementation for testing
- `PostBD`: A placeholder post implementation for testing
- `PostReaderBD`: A placeholder post reader implementation for testing

### Interfaces

- `CHSearchBackendInterface`: Defines the backend API
- `HashtableWithDuplicateKeysInterface`: Extends MapADT to support duplicate keys
- `PostInterface`: Defines the post data structure
- `PostReaderInterface`: Defines methods for reading posts from files

## Functionality

### Data Loading

The backend loads data from files using the Data Wrangler's `PostReader` implementation. It processes each post by:
1. Extracting words from titles and bodies
2. Removing punctuation and converting to lowercase
3. Indexing words with prefixes ("TITLE:" or "BODY:") in the hashtable

### Search Methods

Three search methods are provided:
1. `findPostsByTitleWords()`: Searches only post titles
2. `findPostsByBodyWords()`: Searches only post bodies
3. `findPostsByTitleOrBodyWords()`: Searches both titles and bodies

### Statistics

The `getStatisticsString()` method provides information about:
- Hashtable capacity
- Number of values stored
- Hashtable size

## Testing

The `BackendDeveloper_Tests` class contains comprehensive tests:
- Unit tests for individual backend methods
- Integration tests with DW and AE components
- Partner tests with the Frontend

## How to Run

1. Compile all Java files
2. Run `CHSearchApp` as the main class
3. Alternatively, run `BackendDeveloper_Tests` to execute all test cases

## Dependencies

The backend depends on:
- Data Wrangler's `PostReader` implementation
- Algorithm Engineer's `HashtableWithDuplicateKeys` implementation
- Frontend's interface for user interaction

## Example Usage

```java
// Initialize components
PostReaderInterface postLoader = new PostReaderDW();
HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable = new HashtableWithDuplicateKeysAE<>();
CHSearchBackendInterface backend = new CHSearchBackendBD(hashtable, postLoader);

// Load data
backend.loadData("data/posts.txt");

// Search for posts
List<String> results = backend.findPostsByTitleWords("soup recipe");

// Get statistics
System.out.println(backend.getStatisticsString());
```

## Implementation Notes

- The backend handles punctuation removal and case normalization
- Search results are deduplicated before returning
- The hashtable implementation supports multiple values per key
- Error handling is implemented for file operations and invalid inputs

For more details, see the individual class documentation in the source files.
