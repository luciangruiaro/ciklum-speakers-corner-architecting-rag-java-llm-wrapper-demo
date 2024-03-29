import xml.etree.ElementTree as ET

import requests
from bs4 import BeautifulSoup


def extract_text_from_urls(sitemap_urls):
    documents = []
    for url in extract_urls_from_multiple_sitemaps(sitemap_urls):
        print(url)
        text_chunks = fetch_and_parse(url)
        for chunk in text_chunks:
            if chunk.startswith("Error"):
                print(chunk)
            else:
                documents.append(chunk)
    return documents


def fetch_and_parse(url):
    print(f'Extracting content from {url}')
    try:
        response = requests.get(url)
        response.raise_for_status()  # Will raise an HTTPError if the HTTP request returned an unsuccessful status code
    except requests.RequestException as e:
        return f"Error fetching {url}: {e}"
    soup = BeautifulSoup(response.content, 'html.parser')
    for script in soup(["script", "style"]):  # Remove script and style elements
        script.extract()
    text = soup.get_text()
    words = text.split()
    # Split text into chunks of 2000 words
    chunks = [' '.join(words[i:i + 50]) for i in range(0, len(words), 50)]
    return chunks


def extract_urls_from_multiple_sitemaps(sitemap_urls):
    print('Extracting urls from the configured list of sitemaps...')
    all_urls = []
    print(sitemap_urls)
    for url in sitemap_urls:
        result = extract_urls_from_sitemap(url)
        if isinstance(result, list):
            all_urls.extend(result)
        else:
            print(result)
    return all_urls


def extract_urls_from_sitemap(sitemap_url):
    headers = {'Accept': 'application/xml'}  # Specify that you accept XML responses
    print(f'Extracting urls from {sitemap_url}')
    try:
        response = requests.get(sitemap_url, headers=headers)
        response.raise_for_status()  # Raises an HTTPError if the HTTP request returned an unsuccessful status code
    except requests.RequestException as e:
        return f"Error fetching the sitemap: {e}"
    root = ET.fromstring(response.content)
    urls = [url.find('{http://www.sitemaps.org/schemas/sitemap/0.9}loc').text for url in root]
    return urls
