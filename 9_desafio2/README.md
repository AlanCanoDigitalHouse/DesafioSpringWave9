# Square Meter Calculator

## Disclaimer:

When testing for code coverage the package ```com.example.tucasita.DTO.request.constants.*```
must be excluded. All classes in said package have the single purpose of storing and grouping constants.

## Table of contents

* [Introduction & Technologies](#introduction-and-technologies)
* [Setup](#setup)
* [Usage](#usage)

### Introduction and Technologies

This app will allow you to calculate the size, value, and some other information about a house given through an http
request. This is a Java Server Application made with Springboot using JDK11.

### Setup

The Application should be ready to run as soon as you launch it.

You will want to make sure that the whole project compiles, and you may have to resolve some Maven dependencies.

You'll also want to verify that the JSON files on src.main.resources.static were copied to target.classes.static when
the application is first launched.

The ```districts.json``` file should have a list of 4 districts.

### Usage

The application has a single 'POST' endpoint (```"/calculateForHouse"```) which should have a Json in its body according to the format specified in
[this link](https://drive.google.com/file/d/1Vl7nqxJvrIVwbuipuX8sFnEZSJiuaMJu/view). The list of rooms (environments)
should be called ```"environments"```.
The Application will (for now) not be further developed, as it was simply a challenge to get some practice using
SpringBoot Framework and JUnit + Mockito for Testing.
