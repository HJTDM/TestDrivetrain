// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class Drivetrain extends SubsystemBase {
  private CANSparkMax leftFront = new CANSparkMax(DrivetrainConstants.LEFT_FRONT_ID, MotorType.kBrushless);
  private CANSparkMax rightFront = new CANSparkMax(DrivetrainConstants.RIGHT_FRONT_ID, MotorType.kBrushless);
  private CANSparkMax leftBack = new CANSparkMax(DrivetrainConstants.LEFT_BACK_ID, MotorType.kBrushless);
  private CANSparkMax rightBack = new CANSparkMax(DrivetrainConstants.RIGHT_BACK_ID, MotorType.kBrushless);

  private static final Drivetrain DRIVETRAIN = new Drivetrain();
  public static Drivetrain getInstance(){
    return DRIVETRAIN;
  }

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    leftFront.restoreFactoryDefaults();
    rightFront.restoreFactoryDefaults();
    leftBack.restoreFactoryDefaults();
    rightBack.restoreFactoryDefaults();

    leftBack.follow(leftFront);
    rightBack.follow(rightFront);
    rightFront.setInverted(true);
    rightBack.setInverted(true);
    leftFront.setSmartCurrentLimit(40);
    rightFront.setSmartCurrentLimit(40);
    leftBack.setSmartCurrentLimit(40);
    rightBack.setSmartCurrentLimit(40);

    leftFront.burnFlash();
    rightFront.burnFlash();
    leftBack.burnFlash();
    rightBack.burnFlash();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drive(double throttle, double twist){
    if(Math.abs(throttle) < 0.1){
      throttle = 0;
    }
    if(Math.abs(twist) < 0.1){
      twist = 0;
    }

    leftFront.set(throttle + twist);
    rightFront.set(throttle - twist);
  }

  public void setRightMotor(){
    rightFront.set(0);
  }
}
