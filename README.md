# Spring Boot/Cloud workshop

## Step 0

Implement logic with spring and lombok according to existed tests

See resulted rest controller json output/input in:

* src/test/resources/example.solved.exam.json
* src/test/resources/example.checked.exam.json

## Step 1

Create Theology Service

* Use `@ConfigurationProperty` for populate database by default questions and answers
* Use `JpaRepositry` for connect to database. H2 for example
* Add controller for obtain random count of exercise
* see src/test/resources/example.solved.exam.json exercise block
