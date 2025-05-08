import React, { useState } from 'react';
import OwnedGames from './js/OwnedGames';
import Loading from './js/LoadingMessage';
import ErrorMessage from './js/ErrorMessage';
import './css/App.css';
import './css/SearchModal.css';


function App(){
    const[showSearch, setShowSearch] = useState(false);
    const[input, setInput] = useState('');
    const[results, setResults] = useState([]);
    const[loading, setLoading] = useState(false);
    const[error, setError] = useState(null);

    const openSearch = () => {
        setShowSearch(true);
        setInput('');
        setResults([]);
        setError(null);
    };

    const closeSearch = () => setShowSearch(false);

    const handleSearch = async () => {
        let query = input.trim();
        if(!query) return;

        const isExact = query.startsWith('"') & query.endsWith('"');
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
                <div className="search-modal-overlay" onClick={closeSearch}>
                    <div className="search-modal" onClick={(e) => e.stopPropagation()}>
                        <h2>Board Game Geek Search</h2>
                        <input
                            className="search-input"
                            type="text"
                            value={input}
                            onChange ={(e) => setInput(e.target.value)}
                            onKeyDown={(e) => {
                                if (e.key === 'Enter') {
                                    e.preventDefault();
                                    handleSearch();
                                }
                            }}
                            placeholder='Enter game name (use "quotes" for exact match)'
                        />

                        <div className= "search-modal-buttons">
                            <button onClick={handleSearch} disabled={loading}>Search</button>
                            <button onClick={closeSearch}>Close</button>
                        </div>

                        {loading && <Loading />}
                        {error && <ErrorMessage message={error} />}

                        <ul className="search-results">
                            {results.map(game => (
                                <li key={game.id}>
                                    <strong>{game.nameValue || 'Unnamed Game'}</strong>
                                    {game.yearPublished && `(${game.yearPublished})`}
                                </li>
                            ))}
                        </ul>
                    </div>
                </div>
            )}
        </div>
    );
}

export default App;