# SeekhoAssessment

# App Architecture

### This application is built using the following key technologies and design patterns:

## Architecture Overview

- **Retrofit for Networking:** Efficient API communication and data fetching.
- **Repository Design Pattern:** Centralized data handling, ensuring a clear separation of data sources.
- **MVI (Model-View-Intent) Architecture:** Provides a unidirectional data flow for better state management and predictable UI behavior.
- **Kotlin Flow and Channels:** Used for reactive, asynchronous data streams and event handling, aligning well with the MVI pattern.
- **Jetpack Compose for UI:** Modern, declarative UI framework for building responsive, native UI components.

## Potential Improvements

### To enhance the current architecture, the following improvements are suggested:

- **Local Caching with Room Database:**
    - **Offline Access:** Enables seamless offline functionality, improving the user experience when the network is unavailable.
    - **Reduced API Calls:** Minimizes redundant network requests by storing frequently accessed data locally, reducing latency and server load.
    - **Data Freshness Check:** Use local cache when the data is up-to-date, offering faster loading times.

### Implementing Room as the local database would significantly improve the app's resilience, responsiveness, and overall user experience.


[Screen_recording_20250512_005826.webm](https://github.com/user-attachments/assets/62311996-b6c2-4e3d-8d76-fb9a01d6d3a9)
