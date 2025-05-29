import React, { useState } from 'react';
import './App.css';

function App() {
    const [letter, setLetter] = useState('');
    const [count, setCount] = useState(null);
    const [error, setError] = useState(null);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError(null);
        setCount(null);

        try {
            const response = await fetch(`http://localhost:8080/api/count?letter=${letter}`);
            if (!response.ok) throw new Error('Failed to fetch');
            const data = await response.json();
            setCount(data.count);
        } catch (err) {
            setError('Error fetching city count.');
        }
    };
    return (
        <div className="app">
            <h1>City Counter</h1>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    maxLength="1"
                    value={letter}
                    onChange={(e) => {
                        setLetter(e.target.value.toLowerCase())
                        setCount(null);
                        setError(null);

                    }}
                    placeholder="Enter a letter"
                />
                <button type="submit">Search</button>
            </form>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            {count !== null && <p>Cities starting with '{letter}': {count}</p>}
        </div>
    );
}

export default App;
