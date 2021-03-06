An attempt to adapt trivial Vaadin 8 webapp tutorial (https://www.youtube.com/watch?v=qUBt8k4pQgQ, https://github.com/marcushellberg/spring-boot-todo) to Vaadin 10.

#### Known steps and issues
* `com.vaadin.ui.UI` class is no more accessible. Main class `TodoDiv` extends `Div` and is a `@Route` to root path `""`.
*  `TodoLayout` must be annotated with `@UIScope`. If not, it will stay in `Singleton` scope, 
  which will cause each reload of the page to fail with `java.lang.IllegalStateException: Can't move a node from one state tree to another`. 
* For setting shortcuts, [org.vaadin.marcus.shortcut plugin](https://vaadin.com/directory/component/shortcut) is used.
* `ValoTheme` is not accessible in Vaadin 10. Therefore styling of text fields and header is not done (there seem to be no useful constants, but it can be done via manual styling).
* In `data.sql`, manual `id` insertion is removed. Else the identity will start from 1, and insertion of new elements will fail on primary key duplication.   
  In `Todo` entity, ``@Id`` `strategy` changed to `GenerationType.IDENTITY` (`GenerationType.AUTO` does not use current `identity` value and fails on primary key duplication 
  and will fail on primary key duplication, if pre-inserted records exist).
* Pressing `Ctrl + Enter` with focus in the `add` field will submit an empty field, even if the value in `add` field is non-empty.
* Since the scope of the shortcut is the root `VerticalLayout` now, pressing Enter on any place within it will add a record.
* Auto-saving of changed text field is done after focusing out of this field.  
  I.e. if you reload page without leaving the field, it won't be saved.
* There is no non-empty validation on `add` field.
* Non-empty validation seems to be working for fields withing `TodoLayout` (the empty texts are not saved and replaced back after focusing out of field)
  , but the error label is not displayed.   
  Documentation on validation is [here](https://vaadin.com/docs/v10/flow/binding-data/tutorial-flow-components-binder-validation.html).
* Added the production mode maven profile. To run Vaadin in the production mode, execute maven with `production` profile. See https://vaadin.com/docs/v10/flow/production/tutorial-production-mode-basic.html.
  * Also see https://vaadin.com/forum/thread/17218697, which describes that `vaadin.productionMode=true` has to be put to `application.properties`.
  * To run the project via maven, not IDEA: `mvn clean package -Pproduction -DskipTests`, then in `target` directory `java -jar vaadin-demo-0.0.1-SNAPSHOT.jar`. 