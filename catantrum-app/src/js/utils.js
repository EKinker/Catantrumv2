export function sortSearchResults(results, input) {
    const normalizedInput = input.trim().toLowerCase();

    const isExactMatch = (name) =>
        name?.trim().toLowerCase() === normalizedInput;

    const sortByUsersRated = (a, b) =>
        (b.usersRated || 0) - (a.usersRated || 0);

    const exactMatches = results
        .filter(game => isExactMatch(game.nameValue))
        .sort(sortByUsersRated);

    const partialMatches = results
        .filter(game => !isExactMatch(game.nameValues))
        .sort(sortByUsersRated);

    return [...exactMatches, ...partialMatches];
}