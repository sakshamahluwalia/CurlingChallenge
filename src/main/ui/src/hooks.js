import axios from "axios";
import { useState } from "react";

export const useAxiosFetch = (axiosParams) => {
    const [data, setData] = useState(undefined);
    const [error, setError] = useState(undefined);
    const [loading, setLoading] = useState(true);

    const fetchData = async () => {
        setLoading(true);
        setError(undefined);
        try {
            let response = await axios.request(axiosParams);
            setData(response.data);
        } catch (error) {
            setError(error);
            setLoading(false);
        } finally {
            setLoading(false);
        }
    };

    return { data, error, loading, fetchData };
};