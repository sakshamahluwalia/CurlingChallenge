import React, { useEffect, useRef } from 'react'

const Canvas = (props) => {

    const canvasRef = useRef(null)
    const { points, radius } = props;

    useEffect(() => {
        const canvas = canvasRef.current
        const context = canvas.getContext('2d')
        context.translate(canvas.width/2,canvas.height/2);
        context.scale(1, 2)
        points.map((point) => {
            context.beginPath();
            context.arc(point.x, point.y, radius, 0, 2 * Math.PI, false);
            context.stroke();
        })
    }, [points])

    return (
        <canvas ref={canvasRef} id="canvas"></canvas>
    )
}

export default Canvas