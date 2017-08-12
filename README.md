# Robot Hand Simulator

> **_Keywords:_** _Object-Oriented, Software Architecture, Layered Architecture, Automation_

A simulator of a robot hand (or robot arm) that picks up a set of objects and sort them in a specified order (related objects are placed nearby). This project demonstrates the concept of separating code written in an object-oriented programming language (OOPL) into layered architecture model.

Below is a demo of the simulator software:
<p align="center">
<img src="docs/Demo%20of%20Robot%20Hand%20Simulator.gif" alt="Demo of Robot Hand Simulator" />
</>


## Introduction
The automation software that we will design in this project is to simulate a factory robot that picks and moves some items which have the same color from a given place to the destination place. The software will be designed and implemented by using layered architecture that will be explained shortly. In the layered architecture, we divide the software into components, including: server components, user interface components, control components and coordinator components. The components, then, are structured into the layered architecture such that each component is in a layer where it needs the services provided by components in the lower layers but not the higher layers. Since our software is for only a robot, and users can enter the information of items (products) from GUI, our software will consist of four layers, including: layer 1 kernel server layer, layer 2 workflow server layer, layer 5 hierarchical control layer, and layer 6 user interface layer.

The following figure depicts all layers and their relationships in a general model of layered software architecture.
<p align="center">
<img src="docs/Layered%20Architecture%20Model.png" alt="Layered Architecture Model" />
</>

**_Fig 1: General Model of Layered Architecture -_** *Layered architecture focuses on the grouping of related functionality within an application into distinct layers that are stacked vertically on top of each other. Functionality within each layer is related by a common role or responsibility. Communication between layers is explicit and loosely coupled. Layering our application appropriately helps to support a strong separation of concerns that, in turn, supports flexibility and maintainability.*


## Run Demo
You can download a runnable **.jar** file from the [app](app) directory and run a demo of the simulator on your local machine.

> Note that: although the name of the **.jar** file is **Robot Hand Simulator - Windows GUI.jar**, its GUI is automatically changed and compatible with not only Windows but also the other OSs as well. Java SE Runtime Environment 1.8 or higher is required to run the app, or you can download the whole source code and complie it yourself using Java SE Developmet Kit.


## Source Code Overview
Object-oriented analysis and design was used to separate requirements of building this software. In the next two sub-sections, the source code design and separation will be reviewed in two approaches:
* **Use-Case Approach:** This is the normal and usually the most frequently used method to design an object-oriented software.
* **Layered Approach:** The method that was used to design this software and being discussed here.

### Use-Case Approach
The following figure is the **class diagram** that depicts all relationships between classes and interfaces in the source code.
<p align="center">
<img src="docs/Class%20Diagram.png" alt="Class Diagram" />
</p>

**_Fig 2: Class diagram designed by using use case driven architecture -_** *RBHSim, TableSim, ItemSim are classes that play the role of simulating RobotHand, Table, Item graphically. Open, Close, MoveLeft, MoveRight, MoveTop, MoveBottom, Up, Down are concrete classes that implement interface Operation, so each of the class has execute() and simulate() methods.*

### Layered Approach
Analyzing the software in layered architecture perspective gives us the following diagram.
<p align="center">
<img src="docs/Layer%20Diagram.png" alt="Layer Diagram" />
</p>

**_Fig 3: The overall layered architecture of the simulation software for the factory robot -_** *The blue dashed lines represent the dependencies between layers: Higher layers can utilize services of components of lower layers but not vice versa.*

### Roles of Packages
The following table summarizes the role of each package in the source code. Each **package** _is corresponding to_ a **layer** depicted in figure 1 in the **[Introduction](#introduction)** section.

**Package** | **Description**
----------- | ---------------
[layer1](src/layer1) | **Kernel Server Layer -** This layer contains coordinator components that represent operations the robot’s hand can perform. In addition, layer 1 also contains server components so that higher layers can use services of these components to save the status of the table and color items on the table.
[layer2](src/layer2) | **Workflow Server Layer -** Creating and saving plan of flow of operations that robot’s arm will perform in order to move all necessary items to the desired positions.
[layer3](src/layer3) | **Production Management Server -** Normally, this layer consists of components that represent information about items (location, quantity, type, etc...) at the beginning when the robot’s hand has not performed any operations. In our simulation software, the information is entered via GUI by users. Therefore, this layer does not play any roles in our simulation software.
[layer4](src/layer4) | **Distributed Control Layer -** Normally, this layer consists of components that that provide distributed control for the distributed software systems. In our factory robot automation software, this layer does not play any roles since our software is not designed for distributed system.
[layer5](src/layer5) | **Hierarchical Control Layer -** This layer provide the control for logical business inside the software system, including the control for moving the robot’s hand, picking up and putting down items; and the control for simulating all operations of the robot’s hand graphically.
[layer6](src/layer6) | **User Interface Layer -** This layer consists of user interface components that allow users to enter parameters into the robot control system and display the status of the table with items while picking up and moving items are occurring.

### Additional Material
For more understanding about the design of this software, read this document: 
 [An Architecture-Based Approach for Embedded Systems - Layered Architecture.pdf](https://drive.google.com/file/d/0B42twD7zF0cwMlVyVU9COU1jOUk/view?usp=sharing).

## Author
* **Tran Xuan Hoang**
* **Email:** hoangtx@jaist.ac.jp

## License
This project is licensed under the terms of the [MIT License](LICENSE.md).
