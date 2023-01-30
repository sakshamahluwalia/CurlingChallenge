import React from 'react'

const SimulationStatus = (props) => {

  const {errorWithSimualtion} = props;
  const message = errorWithSimualtion ? "Something went wrong with the simulation! Please try again." : "Set inputs to begin."
  return (
    <span className="simulationStatusText">{message}</span>
  )
}

export default SimulationStatus