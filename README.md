# Basic Simulation Engine for SoS Simulation and Verification

## Current Status of Development

Under the development of the initial project

## Introduction

### Simulation Models of a System-of-Systems

To analyze a System-of-Systems (SoS) before deploying the system, simulation techniques have been utilized as base approaches to observe behaviors of an SoS beforehand. A simulation model(s) of an SoS represents structures and behaviors of an SoS, and they are executable by a particular simulation engine. 

A **Simulation Model (SimModel)** should include all possible entities and objects that can exist in an SoS. In order to adequately define and include the instances, abstract and concrete classes should be appropriately supported by a modeling method. For this reason, our (semi-formal) modeling method follows the definition of classes of a Meta-Model for Systems-of-Systmes, called *M2SoS* [Y.M.Baek, et al., 2018].

> The M2SoS was developed to provide a comprehensive view of an SoS by defining a set of classes and relation-classes at a high level. The M2SoS consists of *Goal*, *Organization*, *Infrastructure*, and *Environment* as higher-level container classes, and *Constituent*, *SystemEntity*, and *ResourceEntity* are defined as components of the container classes.

  Based on the definitions of the M2SoS, a simulation model of this project also defines the container classes and component classes. Also, by providing both fixed and modifiable abstract classes based on meta-models, users (methodologists, system architects / analysts) can implement their simulation models, which is suitable for their own purposes and domains.
  
  > XX interfaces are defined
  
  > XX abstract classes are defined according to the M2SoS and meta-models of <SIMVA-SoS Modeler>

### Simulation and Verification of a System-of-Systems based on This Project

#### Discrete Time Agent-based Simulation

#### Statistical Model Checking (SMC) and SMC-based Analysis

## Instructions

### Dependencies
> Dependencies

### Installation
> Installation

### For Contributors
Contributors of this project may extend this project to conduct an experiment that executes a simulation model(s) of a particular SoS. This project can be differently used for different types of contributers: (i) a methodologist who wants to develop his/her own method for analysis/design/development, (ii) a system engineer who performs actual modeling, verification, or validation.

> For methodologists
> - Something

> For system engineers
> - Something


## More Information

### Future Work
> - XML2Java: Automated model coversion from an XML file to Java-based SoS simulation models
>> Description

> - XML2Property: Automated conversion of property models from an XML file to Java-based verification properties
>> Description

### Other Related Webpages
> Webpage

### Publications
> Publications

## Copyright
Copyright 2017-2021 - SESoS Group in Korea Advanced Institute of Science and Technology (KAIST).
> https://se.kaist.ac.kr
