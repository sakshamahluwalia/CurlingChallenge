import React from 'react'
import { Container } from 'react-bootstrap';
import Canvas from './Canvas';

const SimulationSuccess = (props) => {

    const { points, radius } = props;
    return (
        <>
            <Container>
                <p>
                    {points.map((point) => (
                        <span key={`xOrdinate:${point.x}yOrdinate:${point.y}`}>({point.x}, {parseFloat(point.y).toFixed(7)})</span>
                    ))}
                </p>
            </Container>
            <Container>
                <Canvas points={points} radius={radius} />
            </Container>
        </>
    )
}

export default SimulationSuccess