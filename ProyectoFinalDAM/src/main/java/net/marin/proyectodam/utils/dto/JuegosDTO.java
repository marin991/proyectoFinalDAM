package net.marin.proyectodam.utils.dto;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

@ManagedBean(name = "juegosDTO")
@ViewScoped
public class JuegosDTO implements Serializable {
  
   // @ManagedProperty("#{carService}")
  /*  private CarService service;
 
    private List<Car> cars;
     
    private List<Car> droppedCars;
     
    private Car selectedCar;*/
     
   // @PostConstruct
   /* public void init() {
        cars = service.createCars(9);
        droppedCars = new ArrayList<Car>();
    }
     
    public void onCarDrop(DragDropEvent ddEvent) {
        Car car = ((Car) ddEvent.getData());
  
        droppedCars.add(car);
        cars.remove(car);
    }
     
    public void setService(CarService service) {
        this.service = service;
    }
 
    public List<Car> getCars() {
        return cars;
    }
 
    public List<Car> getDroppedCars() {
        return droppedCars;
    }    
 
    public Car getSelectedCar() {
        return selectedCar;
    }
 
    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }*/
}