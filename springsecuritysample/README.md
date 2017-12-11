# Simple Spring REST CRUD with Basic Authentication Security
## Configuration Details
All Configuration are made with annotations. No XML used. Configuration can be found in `springsecurity.sample.radha.basic.configuration`
JPA Configuration is managed inside `springsecurity.sample.radha.basic.configuration.persistence`
Security Configuration are managed inside `springsecurity.sample.radha.basic.configuration.security`
## Model and Jackson Object Unified
For the quickness of developing the application the `@Entity` Object User is annotated with `@XmlRootElement` so no specific conversion is required in Service Layer
In realtime application the separation of layer is required and so the communication between the objects across layer.
## DAO
In DAO while creating user, no specific verification done for existence of user. In realtime this will not be the case as there will be verification if the same user already exist.
## Database
In memory Embedded Derby DB is used for this sample application. So, the object creation and drop will be managed during the lifetime of the application.

