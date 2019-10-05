# hireme-server

## Running

```bash
     $ mvn spring-boot:run
```

By default, it will run on port 5000. And use MySQL Database on `178.128.170.25:3306`.
You can change it in [application.properties](src/main/resources/application.properties) file

To run test use:
```bash
     $ mvn test
```

## API specification

### Authorization

`/api/auth`

| Method | Path           | Request                                                   | Response                   | Protected |
| :----: | :------------- | --------------------------------------------------------- | -------------------------- | :-------: |
|  POST  | `/auth/signin` | RequestBody: <br> `{usernameOrEmail, password}`           | `{accessToken, tokenType}` |     No    |
|  POST  | `/auth/signup` | RequestBody: <br> `{fullname, username, email, password}` | `{success, message}`       |     No    |

### User

`/api/user`, `/api/users`

| Method | Path                              | Request                        | Response               | Protected |a
| :----: | :-------------------------------- | ------------------------------ | ---------------------- | :-------: |
|   GET  | `/user/me`                        | --                             | `{username, fullname}` |    Yes    |
|  POST  | `/user/me`                        | RequestBody: <br>_UserInfo_    | `{success, message}`   |    Yes    |
|   GET  | `/user/checkUsernameAvailability` | RequestParam: <br>`{username}` | `{isAvailable}`        |     No    |
|   GET  | `/user/checkEmailAvailability`    | RequestParam: <br>`{email}`    | `{isAvailable}`        |     No    |
|   GET  | `/users/{username}`               | PathVariable: <br>`{username}` | _UserInfo_             |     No    |

### Company

`/api/company`

| Method | Path              | Request                             | Response                   | Protected |
| :----: | :---------------- | ----------------------------------- | -------------------------- | :-------: |
|  POST  | `/company`        | RequestBody: <br>_CompanyInfo_      | `{success, message, id}`   |    Yes    |
|   GET  | `/companies/{id}` | PathVariable: <br>`{id}`            | _CompanyInfo_              |     No    |
|   GET  | `/companies/find` | RequestBody: <br>`{name, location}` | [_CompanyInfo_]            |     No    |

```js
UserInfo = {
  username,             // 'the_7th_hokage'
  location,             // 'Konoha'
  education: {          // Optional
    university,         // 'Konoha Ninja Academy'
    graduation,         // 'June 2006'
    major,              // 'Ninja', 'Computer Science'
    degree,             // 'Genin', 'Bachelor'
  },
  hidden,               // true         Show profile in search?
  strong_skill: {
    name,               // 'Shadow Clone Technique'
    description         // 'I can create thousands independent shadow clones, each capable of using other ninja technique'
  },
  urls: {
    github:             // 'https://github.com/the-7th-hokage',
    linked_in:          // 'https://linkedin.com/in/the-7th-hokage',
    web:                // 'https://naruto.uzumaki.konoha/in/the-7th-hokage',
  }
  skills,               // ['react', 'spring', 'kotlin', 'java', 'tensorflow']
  employment: {         // Optional
    company,            // 'Konoha'
    role,               // 'Hokage'
    reference: {        // Optional
      name:             // 'Shikamaru Nara',
      numer:            // '+7 (700) 101 22 33'
    }
  },
  createdAt,            // Date when entry was created
}

CompanyInfo = {
  id,                   // Only in response
  name,                 // 'Google'
  location,             // 'Astana, Kazakhstan'
  specialization,       // 'Search Engine'
  employees,            // '100'        Number of employees
  experience,           // '21 years'
  hidden,               // false        Show in search
  urls: {
    github:             // 'https://github.com/goolge',
    linked_in:          // 'https://linkedin.com/in/google',
    web:                // 'https://google.com',
  },
  creator: {
    username,           // 'the_7th_hokage'
    fullname,           // 'Naruto Uzumaki'
    role,               // 'Hokage'         Founder/HR/Team leader
    linked_in:          // 'https://linkedin.com/in/the-7th-hokage',
  },
  logo_url,             // https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png
  createdAt,            // Date when entry was created
}
```

### Job Offer

`/api/job-offer`

| Method | Path                          | Request                             | Response                   | Protected |
| :----: | :---------------------------- | ----------------------------------- | -------------------------- | :-------: |
|  POST  | `/job-offer`                  | RequestBody: <br>_JobOfferInfo_     | `{success, message, id}`   |    Yes    |
|   GET  | `/job-offers/{id}`            | PathVariable: <br>`{id}`            | _JobOfferInfo_             |    Yes    |
|   GET  | `/job-offers/find-by-company` | RequestBody: <br>`{company_id}`     | [_JobOfferInfo_]           |    Yes    |
|   GET  | `/job-offers/find-by-position`| RequestBody: <br>`{position}`       | [_JobOfferInfo_]           |    Yes    |
|   GET  | `/job-offers/find-by-location`| RequestBody: <br>`{location}`       | [_JobOfferInfo_]           |    Yes    |

```js
JobOfferInfo = {
  id,                   // only in response
  company: {
      company_id,
      name,             // only in response
      logo              // only in response
  },
  position,
  responsibilities,          
  qualifications,
  [location]            // list of locations
  created_at,           // only in response
  updated_at            // only in response
}
```

### Posts (news feed) - returns the last 10 posts for now

`/api/post`

| Method | Path               | Request                          | Response                   | Protected |
| :----: | :------------------| -------------------------------- | -------------------------- | :-------: |
|  POST  | `/post`            | RequestBody: <br>_PostForm_     | `{success, message, id}`   |    Yes    |
|   GET  | `/posts/{id}`      | PathVariable: <br>`{id}`         | _Post_                     |    Yes    |
|   GET  | `/posts`           | --                               | [_Post_]                   |    Yes    |

Used in response
```js
Post = {
  id,                      
  company,                  // is company (true - author is company, false - author is user)
  author: {
      id,                   // company id for company or user id for user
      name,                 // fullname for user or company name for company
      avatar_url,
  },
  text,
  tags,
  is_offer, bool
  offer: {
    JobOfferInfo
  }
  created_at
}

```

Used in request
```js
PostForm = {
  company,                 // is company (true - author is company, false - author is user)
  author,                  // company id for company or user id for user
  title,
  text,
  jobOffersIds             // list of ids of job offers
}

```
