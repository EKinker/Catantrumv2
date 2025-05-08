import React, { useState } from 'react';
import OwnedGames from './js/OwnedGames';
import SearchModal from './js/SearchModal'; // ⬅️ New import
import './css/App.css';

function App() {
    const [showSearch, setShowSearch] = useState(false);
    const [input, setInput] = useState('');
    const [results, setResults] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const openSearch = () => {
        setShowSearch(true);
        setInput('');
        setResults([]);
        setError(null);
    };

    const closeSearch = () => setShowSearch(false);

    const handleSearch = async () => {
        let query = input.trim();
        if (!query) return;

        const isExact = query.startsWith('"') && query.endsWith('"');
        query = isExact ? query.slice(1, -1) : query;

        setLoading(true);
        setError(null);

        try {
            const response = await fetch(`/api/search/games?query=${encodeURIComponent(query)}&exact=${isExact}`);
            if (!response.ok) throw new Error('Failed to fetch');
            const data = await response.json();
            setResults(data);
        } catch (err) {
            setError(err.message || 'Unknown error');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="App">
            <h1>Games List</h1>
            <button onClick={openSearch}>Search</button>
            <OwnedGames />

            {showSearch && (
                <SearchModal
                    input={input}
                    setInput={setInput}
                    results={results}
                    loading={loading}
                    error={error}
                    onClose={closeSearch}
                    onSearch={handleSearch}
                />
            )}
        </div>
    );
}

export default App;
