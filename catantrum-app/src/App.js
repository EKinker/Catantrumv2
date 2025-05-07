import React, { useState } from 'react';
import OwnedGames from './js/OwnedGames';
import './css/App.css';
import './css/SearchModal.css';

function App(){
    const[showSearch, setShowSearch] = useState(false);

    const openSearch = () => setShowSearch(true);
    const closeSearch = () => setShowSearch(false);

    return (
        <div className="App">
            <h1>Games List</h1>
            <button onClick={openSearch}>Search</button>
            <OwnedGames />

            {showSearch && (
                <div className="search-modal-overlay" onClick={closeSearch}>
                    <div className="search-modal" onClick={(e) => e.stopPropagation()}>
                        <h2>Search Games on Board Game Geek</h2>

                        <button onClick={closeSearch}>Close</button>
                    </div>
                </div>
            )}
        </div>
    );
}

export default App;