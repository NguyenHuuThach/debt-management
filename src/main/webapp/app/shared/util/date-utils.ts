import dayjs from 'dayjs';

import { APP_LOCAL_DATETIME_FORMAT, APP_LOCAL_DATE_FORMAT_2 } from 'app/config/constants';

export const convertDateTimeFromServer = date => (date ? dayjs(date).format(APP_LOCAL_DATETIME_FORMAT) : null);

export const convertDateTimeToServer = date => (date ? dayjs(date).toDate() : null);

export const displayDefaultDateTime = () => dayjs().startOf('day').format(APP_LOCAL_DATETIME_FORMAT);

export const displayDefaultDate = date => (date ? dayjs().format(APP_LOCAL_DATE_FORMAT_2) : null);
