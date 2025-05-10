import React from 'react';
import Loading from './LoadingMessage';
import ErrorMessage from './ErrorMessage';
import '../css/SearchModal.css';
import { sortSearchResults } from './utils';

function SearchModal({ input, setInput, results, loading, error, onClose, onSearch }) {
    return (
        <div className="search-modal-overlay" onClick={onClose}>
            <div className="search-modal" onClick={(e) => e.stopPropagation()}>
                <h2>Board Game Geek Search</h2>
                <input
                    className="search-input"
                    type="text"
                    value={input}
                    onChange={(e) => setInput(e.target.value)}
                    onKeyDown={(e) => {
                        if (e.key === 'Enter') {
                            e.preventDefault();
                            onSearch();
                        }
                    }}
                    placeholder='Enter game name (use "quotes" for exact match)'
                />

                <div className="search-modal-buttons">
                    <button onClick={onSearch} disabled={loading}>Search</button>
                    <button onClick={onClose}>Close</button>
                </div>

                {loading && <Loading />}
                {error && <ErrorMessage message={error} />}

                <ul className="search-results">
                    {sortSearchResults(results,input).map((game) => (
                        <ul key={game.id}>
                            <strong>{game.nameValue || 'Unnamed Game'}</strong>
                            {game.yearPublished && ` (${game.yearPublished})`}
                        </ul>
                    ))}
                </ul>
            </div>
        </div>
    );
}

export default SearchModal;
