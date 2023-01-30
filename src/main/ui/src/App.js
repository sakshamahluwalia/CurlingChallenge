import "./App.css"
import { useEffect, useState } from 'react';
import { useAxiosFetch } from './hooks';

import Form from 'react-bootstrap/Form';
import Alert from 'react-bootstrap/Alert';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import InputGroup from 'react-bootstrap/InputGroup';
import SimulationStatus from "./Components/SimulationStatus";
import SimulationSuccess from "./Components/SimulationSuccess";

const App = () => {

  const [numDisks, setNumDisks] = useState(0);
  const [radius, setRadius] = useState(0);
  const [answers, setAnswers] = useState([]);


  const [diskSetSuccessfully, setDiskSetSuccessfully] = useState(false);
  const [radiusSetSuccessfully, setRadiusSetSuccessfully] = useState(false);

  const [errorWithDisks, setErrorWithDisks] = useState(false);
  const [errorWithRadius, setErrorWithRadius] = useState(false);
  const [errorWithSimualtion, setErrorWithSimualtion] = useState(false);

  const { data: setDisksData, error: setDisksError, loading: setDisksIsLoading, fetchData: setDisksCB } = useAxiosFetch({
    method: "POST",
    url: "/api/set/points",
    data: {
      "numbersToGenerate": numDisks
    }
  });

  useEffect(() => {
    setDisksError ? updateErrorStates("disk", true, false) : setDisksData && updateErrorStates("disk", false, true)
  }, [setDisksData, setDisksIsLoading, setDisksError])

  const { data: setRadiusData, error: setRadiusError, loading: setRadiusIsLoading, fetchData: setRadiusCB } = useAxiosFetch({
    method: "POST",
    url: "/api/set/radius",
    data: {
      radius
    }
  });

  useEffect(() => {
    setRadiusError ? updateErrorStates("radius", true, false) : setRadiusData && updateErrorStates("radius", false, true)
  }, [setRadiusData, setRadiusIsLoading, setRadiusError])

  const { data: startSimulationData, error: startSimulationError, loading: startSimulationIsLoading, fetchData: startSimulationCB } = useAxiosFetch({
    method: "GET",
    url: "/api/startSimulation"
  });

  useEffect(() => {
    startSimulationError ? setErrorWithSimualtion(true) : startSimulationData && setAnswers(startSimulationData);
  }, [startSimulationData, startSimulationIsLoading, startSimulationError])

  const handleNumDiskChange = (e) => {
    // perform validation on input
    // be number format
    setErrorWithDisks(false)
    setNumDisks(e.target.value)
  }

  const handleRadiusChange = (e) => {
    // perform validation on input
    // be number format
    setErrorWithRadius(false)
    setRadius(e.target.value)
  }

  const updateErrorStates = (event, errorValue, successValue) => {
    switch (event) {
      case "disk":
        setErrorWithDisks(errorValue)
        setDiskSetSuccessfully(successValue)
        break;
      case "radius":
        setErrorWithRadius(errorValue)
        setRadiusSetSuccessfully(successValue)
        break;

      default:
        break;
    }
  }

  useEffect(() => {
    setTimeout(() => {
      setDiskSetSuccessfully(false)
    }, 1000);
  }, [diskSetSuccessfully])

  useEffect(() => {
    setTimeout(() => {
      setRadiusSetSuccessfully(false)
    }, 1000);
  }, [radiusSetSuccessfully])

  return (

    <Container className="p-3">
      <Container className="p-3">
        <h1 className="header">Curling Challenge</h1>

        <InputGroup className="mb-3">
          <Form.Control
            placeholder="Input number of disks"
            aria-label="Input number of disks"
            aria-describedby="Input number of disks"
            onChange={handleNumDiskChange}
          />
          <Button onClick={setDisksCB} variant="outline-secondary" id="Update number of disks">
            Set Disks
          </Button>
        </InputGroup>

        <InputGroup className="mb-3">
          <Form.Control
            placeholder="Input radius of disks"
            aria-label="Input radius of disks"
            aria-describedby="Input radius of disks"
            onChange={handleRadiusChange}
          />
          <Button onClick={setRadiusCB} variant="outline-secondary" id="Update Radius">
            Set Radius
          </Button>
        </InputGroup>

        <Button onClick={startSimulationCB} disabled={!numDisks || !radius}>Start Simulation</Button>

      </Container>

      <Container>
        <Alert variant="success" show={diskSetSuccessfully}>
          Disks Set
        </Alert>
        <Alert variant="success" show={radiusSetSuccessfully}>
          Radius Set
        </Alert>
        <Alert variant="danger" show={errorWithDisks}>
          Error setting number of disks
        </Alert>
        <Alert variant="danger" show={errorWithRadius}>
          Error setting radius
        </Alert>
      </Container>

      <Container className="simulationStatusText">
        {(answers && answers.length > 0) ? (<SimulationSuccess points={answers} radius={radius} />) : (<SimulationStatus showError={errorWithSimualtion} />)}
      </Container>

    </Container>
  );
}

export default App;
