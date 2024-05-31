# AudioLibrary

This is a simple audio library system implemented with Spring Boot. It includes features for managing songs, playlists, and users, as well as audit logging and user authentication.

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
  - [Song Management](#song-management)
  - [Playlist Management](#playlist-management)
  - [Authentication](#authentication)
  - [Audit Logs](#audit-logs)

## Installation
  
1. Clone the repository:

```bash
git clone https://github.com/darialapadus/audiolibrary.git
cd audiolibrary
```

2. Build the project using Maven:

```bash
mvn clean install
```
3. Run the application:

```bash
mvn spring-boot:run
```
## Usage

After starting the application, you can use a tool like Postman or cURL to interact with the API. The base URL for all endpoints is 'http://localhost:8080'.

## API Endpoints

  ## Song Management
   
  ## Create a Song

```bash
POST /songs/create
```

Parameters:

- name: String (name of the song)
- artist: String (artist of the song)
- releaseYear: int (year of release)
- username: String (name of the user performing the action)

   ## Search Songs by Name

```bash
GET /songs/searchByName
```
Parameters:

- name: String (name of the song)
- page: int (page number, default is 0)
- size: int (page size, default is 10)
- username: String (name of the user performing the action)

  ## Search Songs by Artist

```bash
GET /songs/searchByArtist
```
Parameters:

- artist: String (artist of the song)
- page: int (page number, default is 0)
- size: int (page size, default is 10)
- username: String (name of the user performing the action)

  ## Playlist Management

  ## Create a Playlist

```bash
POST /playlists/create
```
Parameters:

- name: String (name of the playlist)
- owner: JSON (user object of the owner)

  ## Add Song to Playlist

```bash
POST /playlists/addSong
```
Parameters:

- playlistId: Long (ID of the playlist)
- songId: Long (ID of the song)
- ownerId: Long (ID of the owner)

  ## List Playlists

```bash
GET /playlists/list
```
Parameters:

- ownerId: Long (ID of the owner)
- page: int (page number, default is 0)
- size: int (page size, default is 10)

  ## Export Playlist

```bash
GET /playlists/export/{playlistId}/{format}
```
Parameters:

- playlistId: Long (ID of the playlist)
- userId: Long (ID of the user exporting the playlist)
- format: String (export format, either 'csv' or 'json')

  ## Authentication

  ## Register

```bash
POST /auth/register
```
Parameters:

- username: String (username of the new user)
- password: String (password of the new user)

  ## Login

```bash
POST /auth/login
```
Parameters:

- username: String (username of the user)
- password: String (password of the user)

  ## Logout

```bash
POST /auth/logout
```
Parameters: None

  ## Promote User

```bash
POST /auth/promote
```
Parameters:

- username: String (username of the user to promote)

  ## Audit Logs
