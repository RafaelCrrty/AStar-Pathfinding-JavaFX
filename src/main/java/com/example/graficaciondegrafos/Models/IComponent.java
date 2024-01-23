package com.example.graficaciondegrafos.Models;

abstract class IComponent {

    abstract void notify(Object message);

    abstract void receive(Object message);
}
