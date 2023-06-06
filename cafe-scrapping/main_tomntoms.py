import json
import requests
from bs4 import BeautifulSoup
import time


# TODO: '서비스 안내' 부분 추가 필요 (24h, Wifi, ...)
def get_document(store_id):
    time.sleep(0.1)
    print(store_id)
    url = 'https://tomntoms.com/pop/pop_store_info.html?uid={}'.format(store_id)
    html = requests.get(url)
    soup = BeautifulSoup(html.text, 'html.parser')
    store_names = soup.css.select('div.pop-con.pop-store-info > div > div.pop-title > h2')
    addresses = soup.css.select('div.pop-con.pop-store-info > div > div.pop-body > div.info-area > div.scroll-y-area > div:nth-child(1) > p.desc')
    contact_numbers = soup.css.select('div.pop-con.pop-store-info > div > div.pop-body > div.info-area > div.scroll-y-area > div:nth-child(2) > a')
    opening_hours = soup.css.select('div.pop-con.pop-store-info > div > div.pop-body > div.info-area > div.scroll-y-area > div:nth-child(3) > p.desc')
    store_images = soup.css.select('div.pop-con.pop-store-info > div > div.pop-body > div.img-area > div.store-info-slide > div > div > div > img')

    result = {'name': store_names[0].text.strip() if len(store_names) > 0 else '',
              'address': addresses[0].text.strip() if len(addresses) > 0 else '',
              'contactNumber': contact_numbers[0].text.replace('T.', '').strip() if len(contact_numbers) > 0 else '',
              'openingHours': opening_hours[0].text.strip() if len(opening_hours) > 0 else '',
              'imageUrls': [('https://tomntoms.com' + image['src']) for image in store_images],
              'providerStoreId': store_id
              }
    return result


def is_valid(document):
    return document['name'] != ''


def main():
    documents = []
    for i in range(1, 500):
        document = get_document(i)
        if is_valid(document):
            documents.append(document)
    dumps = json.dumps(documents, ensure_ascii=False).encode('utf8')
    with open("tomntoms.json", "w") as out_file:
        out_file.write(dumps.decode())


if __name__ == '__main__':
    main()
