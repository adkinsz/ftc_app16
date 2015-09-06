package com.qualcomm.ftcrobotcontroller;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.Range;

public class MockServo extends Servo {

  private double devicePosition;
  private double normalizedPosition;
  public MockServo(ServoController controller, int portNumber){
    this(controller, portNumber, Direction.FORWARD);
  }

  public MockServo(ServoController controller, int portNumber, Direction direction) {
    super(controller, portNumber, direction);
    this.controller = null;
    this.portNumber = -1;
    this.direction = Direction.FORWARD;
    this.minPosition = 0.0D;
    this.maxPosition = 1.0D;
    this.direction = direction;
    this.controller = controller;
    this.portNumber = portNumber;
  }

  @Override
  public String getConnectionInfo() {
    return "Simulation Device; port " + this.portNumber;
  }

  @Override
  public void setPosition(double position) {

    normalizedPosition = position;

    if(this.direction == Direction.REVERSE) {
      position = this.Reverse(position);
    }

    devicePosition = Range.scale(position, 0.0D, 1.0D, this.minPosition, this.maxPosition);

  }

  @Override
  public double getPosition() {
    double position = devicePosition;
    if(this.direction == Direction.REVERSE) {
      position = this.Reverse(position);
    }

    double normalizedPosition = Range.scale(position, this.minPosition, this.maxPosition, 0.0D, 1.0D);
    return Range.clip(normalizedPosition, 0.0D, 1.0D);
  }

  private double Reverse(double value) {
    return MAX_POSITION - value;
  }
}
