import React, { useState } from 'react';
import './App.css';

const CitySearch = () => {
    const [letter, setLetter] = useState('');
    const [cityCount, setCityCount] = useState(null);
    const [cities, setCities] = useState([]);
    const [error, setError] = useState('');

    const handleSearch = async () => {
        if (!letter || !/^[A-Za-z]$/.test(letter)) {
            setError('Please enter a single valid letter (A-Z).');
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/api/cities?letter=${letter.toUpperCase()}`);
            const data = await response.json();
            setCityCount(data.count);
            setCities(data.cities ? data.cities.split(", ").map(name => name.trim()) : []);
            setError('');
        } catch (err) {
            setError('Error fetching city data.');
        }
    };

    return (
        <div className="container">
            <h2 className="header">City Search by Starting Letter</h2>
            <p className="overview">
                Enter a letter to find how many world cities start with it. Results include the count and a sample list of city names fetched from the OpenWeatherMap data.
            </p>

            <div className="input-group">
                <input
                    type="text"
                    maxLength="1"
                    value={letter}
                    onChange={(e) => setLetter(e.target.value)}
                    placeholder="Enter a letter (A-Z)"
                    className="input-field"
                />
                <button onClick={handleSearch} className="button">
                    Search
                </button>
            </div>

            {error && <p className="error">{error}</p>}

            {cityCount !== null && (
                <div className="result">
                    <p><strong>Total Cities Found:</strong> {cityCount}</p>
                    {cities.length > 0 && (
                        <ul className="city-list">
                            {cities.map((city, index) => (
                                <li key={index}>{city}</li>
                            ))}
                        </ul>
                    )}
                </div>
            )}
        </div>
    );
};

export default CitySearch;
