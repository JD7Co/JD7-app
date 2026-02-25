// Authorization token that must have been created previously. See : https://developer.spotify.com/documentation/web-api/concepts/authorization
const token = 'BQBLvqt4bLSRVXpR7W31tXDOFPvdFQzC0ulDI1xAZLVfwCxtlzb3GHofY0o6raIq8taeTaVihSkfCPdouWFZCDqdTtjsUUUqEnXHrFNGQefyIIGW4arcF7-cB5ZL1JgGc5VjeaC9qW9gF1J1H9toquN1Za151lczGsmRcavSfCPtKlkHHSDIRPmGmSh2NTEXy-jyg-EJij6aIPe5YFDPY_3j9izbPujJxO5ekQzkx3wVMrdrANpVYtlSZyDamcrfc7wvHV4q53iTOpEqKz_BnKWCeD3PuGvuFi-5IDWOq0ggtOiKHbdFKycSxqq5cc7fgY96Zsmy';
async function fetchWebApi(endpoint, method, body) {
  const res = await fetch(`https://api.spotify.com/${endpoint}`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
    method,
    body:JSON.stringify(body)
  });
  return await res.json();
}

async function getTopTracks(){
  // Endpoint reference : https://developer.spotify.com/documentation/web-api/reference/get-users-top-artists-and-tracks
  return (await fetchWebApi(
    'v1/me/top/tracks?time_range=long_term&limit=5', 'GET'
  )).items;
}

const topTracks = await getTopTracks();
console.log(
  topTracks?.map(
    ({name, artists}) =>
      `${name} by ${artists.map(artist => artist.name).join(', ')}`
  )
);