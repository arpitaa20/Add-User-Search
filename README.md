Here , at first create a maven Project named as Add,user search built using Spring Boot for the back‑end with JPA to interact with a PostgreSQL database
where the User entity is annotated with @Entity and @Table like with a unique constraint on the username to map Java objects to the database table and fields are marked with @Id, 
@GeneratedValue and @Column to define primary keys and column attributes or properties. The UserRepository interface extends JpaRepository to provide CRUD operations
while the EncryptionUtil class is annotated with @Component offers AES‑256 encryption/decryption for securing credentials. A custom AuthenticationFilter is also a @Component
provide the requests to critical endpoints like /addUser and /queryUser and it validate the basicAuthentication header comparing it with admin credentials defined in the configuration.
In the backend part the API endpoints that the front end communicates with are defined within the Controller package under the Controller package. 
For example like endpoints such as /addUser and /queryUser are implemented in our REST controller classe. 
These controllers handle incoming JSON requests from the front end part which are forwarded via a proxy configuration in the front end package.json
and use the Repository package to interact with the PostgreSQL database using JPA entities defined in the Entity package. Security for these endpoints is ensured by the
AuthenticationFilter located in the Filter package which is validated the basic Authentication credentials and the EncryptionUtil in Util is used to secure sensitive data. 
This modular backend architecture allows the front end to back end and consume the backend services via well-defined RESTful APIs.
